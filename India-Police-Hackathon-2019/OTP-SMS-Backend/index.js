const express = require('express');
var bodyParser = require('body-parser');
const cors = require('cors');
const dotenv = require('dotenv');
const testRoute = require('./routes/test');

const router = express.Router();
const app = express();

dotenv.config();

// routes
const userRoute = require('./routes/user');

// middleware
app.use(cors());
app.use(bodyParser.json({ limit: '50mb' }));
app.use(bodyParser.urlencoded({ limit: '50mb', extended: true }));

router.use(testRoute);

app.use((req, res, next) => {
    console.log(`${new Date().toString()} => ${req.originalUrl}`);
    next();
});


const port = process.env.PORT || 4060;

app.use('/api/', router);

app.listen(port, () => console.log(`Example app listening on port ${port}!`));
