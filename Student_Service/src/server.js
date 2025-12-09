const app = require("./app");
const connectDB = require("./config/db");
require("dotenv").config();

const PORT = process.env.PORT || 4000;

// Connect to DB
connectDB();

// Start server
app.listen(PORT, () => {
  console.log(`🚀 Student Service running on port ${PORT}`);
});
