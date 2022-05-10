const router = require("express").Router();
const User = require("../models/User");
const Post = require("../models/Post");

//Create Post
router.post("/", async (req, res) => {
  const newPost = new Post(req.body);
  try {
    const savedPost = await newPost.save();
    // 같은 제목으로 저장안되는건 Post.js의 unique 값 때문
    res.status(200).json(savedPost);
  } catch (err) {
    res.status(500).json(err);
  }
});

//Update Post
router.put("/:id", async (req, res) => {
  //post를 찾고 post의 username이 지금의 username과 일치하는지 비교
  try {
    const post = await Post.findById(req.params.id);
    if (post.username === req.body.username) {
      try {
        const updatedPost = await Post.findByIdAndUpdate(
          req.params.id,
          {
            $set: req.body,
          },
          { new: true }
        );
        res.status(200).json(updatedPost);
      } catch (error) {
        res.status(500).json(err);
      }
    } else {
      res.status(401).json("You can only update your post");
    }
  } catch (error) {
    res.status(500).json(err);
  }
});

//Delete Post
router.delete("/:id", async (req, res) => {
    //post를 찾고 post의 username이 지금의 username과 일치하는지 비교
    try {
      const post = await Post.findById(req.params.id);
      if (post.username === req.body.username) {
        try {
          await post.delete();
          res.status(200).json("The post has been deleted");
        } catch (error) {
          res.status(500).json(err);
        }
      } else {
        res.status(401).json("You can only delete your post");
      }
    } catch (error) {
      res.status(500).json(err);
    }
  });


//Get Post
router.get("/:id", async (req, res) => {
  try {
    const user = await User.findById(req.params.id);
    const { password, ...others } = user._doc;
    res.status(200).json(others);
  } catch (err) {
    res.status(500).json(err);
  }
});

module.exports = router;
