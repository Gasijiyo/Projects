const express = require("express");
const app = express();
const dotenv = require("dotenv");   //.env는 보안으로 gitignore처리. (mongo의 cluster)
const mongoose = require("mongoose")
const authRoute = require("./routes/auth")
const userRoute = require("./routes/users")

dotenv.config();
app.use(express.json());

mongoose
    .connect(process.env.Mongo_url)
    .then(console.log("Connected to MongoDB"))
    .catch(err => console.log(err));

app.use("/api/auth", authRoute);
app.use("/api/users", userRoute);

app.listen("5000", ()=>{
    console.log('Backend is running');
})