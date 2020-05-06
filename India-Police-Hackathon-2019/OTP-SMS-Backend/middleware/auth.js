const jwt = require('jsonwebtoken');
const User = require('../models/User.model');

const auth = async (req, res, next) => {
    try {
        const token = req.get('Authorization').replace('Bearer ', '');

        const data = jwt.verify(token, `${process.env.SP_JWT_SECRET}`);
        const user = await User.findOne({
            _id: data._id,
            'tokens.token': token
        });
        if (!user) {
            throw new Error();
        }
        req.user = user;
        req.token = token;
        next();
    } catch (error) {
        res.status(401).json({
            error: 'Not authorized to access this resource'
        });
    }
};
module.exports = auth;
