import "./navbar.css"
import { Link } from "react-router-dom";

export default function Navbar() {
    // true 면 logout 보임, false면 안보임
    const user = true;
  return (
    <div className="top">
        <div className="topLeft">
            <i className="topIcon fa-brands fa-facebook-square"></i>
            <i className="topIcon fa-brands fa-twitter-square"></i>
            <i className="topIcon fa-brands fa-instagram-square"></i>
            <i className="topIcon fa-brands fa-github-square"></i>
        </div>
        <div className="topCenter">
            <ul className="topList">
                <li className="topListItem">
                    <Link className="link" to="/" >HOME</Link>
                </li>
                <li className="topListItem">
                    <Link className="link" to="/" >ABOUT</Link>
                </li>
                <li className="topListItem">
                    <Link className="link" to="/" >CONTACT</Link>
                </li>
                <li className="topListItem">
                    <Link className="link" to="/write" >WRITE</Link>
                </li>
                <li className="topListItem">
                    {user && "LOGOUT"}
                    {/* <Link className="link" to="/" >LOGOUT</Link> */}
                </li>
            </ul>
        </div>
        <div className="topRight">
            {user ? (
                <Link className="link" to="/settings">
                    <img className="topImg" src="1.jpg" alt="" />
                </Link>                    
                ) : (
                    <ul className="topList">
                        <li className="topListItem">
                            <Link className="link" to="/login" >
                                LOGIN
                            </Link>
                        </li>
                        <li className="topListItem">
                            <Link className="link" to="/register" >
                                REGISTER
                            </Link>
                        </li>                    
                    </ul>
                )
            }
            <i className="topSearchIcon fa-solid fa-magnifying-glass"></i>
        </div>
      
    </div>
  )
}
