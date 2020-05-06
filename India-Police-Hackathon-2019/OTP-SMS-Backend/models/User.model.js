let mongoose = require('mongoose');
let validator = require('validator');
let bcrypt = require('bcryptjs');
let jwt = require('jsonwebtoken');
let { Schema } = mongoose;

const UserSchema = mongoose.Schema({
    name: {
        type: String,
        trim: true,
        default: 'John Doe'
    },
    email: {
        type: String,
        required: true,
        unique: true,
        lowercase: true,
        validate: (value) => {
            if (!validator.isEmail(value))
                throw new Error('Invalid Email address');
        }
    },
    type: {
        type: String,
        required: true
    },
    password: {
        type: String,
        required: true,
        minLength: 7
    },
    tokens: [
        {
            token: {
                type: String,
                required: true
            }
        }
    ]
});

UserSchema.pre('save', async function(next) {
    const user = this;
    if (user.isModified('password'))
        user.password = await bcrypt.hash(user.password, 8);

    next();
});

UserSchema.methods.generateAuthToken = async function() {
    const user = this;
    const token = jwt.sign({ _id: user._id }, `${process.env.JWT_SECRET}`);
    user.tokens = user.tokens.concat({ token });
    await user.save();
    return token;
};

UserSchema.statics.findByCredentials = async (email, password) => {
    const user = await User.findOne({ email });
    if (!user) throw new Error('Invalid login credentials user');

    const isPasswordMatch = await bcrypt.compare(password, user.password);
    if (!isPasswordMatch) throw new Error('Invalid login credentials');

    return user;
};

const User = mongoose.model('User', UserSchema);

module.exports = User;
