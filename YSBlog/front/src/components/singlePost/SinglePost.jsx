import { Link } from "react-router-dom";
import "./singlepost.css"

export default function SinglePost() {
  return (
    <div className="singlePost">
        <div className="singlePostWrapper">
            <img className="singlePostImg" src="444.jpg" alt=""/>
            <h1 className="singlePostTitle">
                Yesterday...Lorem            
                <div className="singlePostEdit">
                    <i className="singlePostIcon fa-solid fa-pen-to-square"></i>
                    <i className="singlePostIcon fa-solid fa-trash"></i>
                </div>
            </h1>
            <div className="singlePostInfo">
                <span className="singlePostAuthor">
                    Author: 
                    <b>
                        <Link className="link" to="/posts?username=YangSu">
                            YangSu
                        </Link>
                    </b>
                </span>                
                <span className="singlePostDate">1 hour ago</span>
            </div>
            <p className="singlePostDesc">
                Yesterday
                All my trouble seemed so far away
                Now it looks as though they're here to stay
                Oh, I believe in yesterday
                Suddenly
                I'm not half the man I used to be
                There's a shadow hanging over me
                Oh, yesterday came suddenly
                <br/>
                Why she had to go, I don't know
                She wouldn't say
                I said something wrong
                Now I long for yesterday
            </p>
        </div>
    </div>
  )
}
