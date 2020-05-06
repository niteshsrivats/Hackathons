let express = require('express');
let extend = require('lodash/extend');
let router = express.Router();

const auth = require('../middleware/auth');
const User = require('../models/User.model');

router.post('/register', async (req, res) => {
    try {
        const user = new User(req.body);
        await user.save();
        const token = await user.generateAuthToken();
        res.status(201).json({ token });
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});

router.post('/login', async (req, res) => {
    try {
        const { email, password } = req.body;
        const user = await User.findByCredentials(email, password);
        if (!user) {
            return res.status(401).send({
                error: 'Login failed! Check authentication credentials'
            });
        }
        const token = await user.generateAuthToken();
        res.json({ token });
    } catch (error) {
        res.status(400).json({ error: error.message });
    }
});

router.get('/profile', auth, async (req, res) => {
    const { name, email, type } = req.user;
    const id = req.user._id;
    const user = {
        id,
        name,
        email,
        type
    };
    res.json(user);
});

router.post('/profile/logout', auth, async (req, res) => {
    try {
        req.user.tokens = req.user.tokens.filter((token) => {
            return token.token != req.token;
        });
        await req.user.save();
        res.json({ status: 'success' });
    } catch (error) {
        res.status(500).json({ status: error.message });
    }
});

router.post('/profile/logoutall', auth, async (req, res) => {
    try {
        req.user.tokens.splice(0, req.user.tokens.length);
        await req.user.save();
        res.json({ status: 'success' });
    } catch (error) {
        res.status(500).json({ status: error.message });
    }
});

module.exports = router;
