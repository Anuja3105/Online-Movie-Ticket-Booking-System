import { useState } from "react";
import { Link } from "react-router-dom";
import "./index.css";
import axios from "axios";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";
import { URL } from "../../config";

const Signin = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const signinUser = () => {
    if (email.length === 0) {
      toast.warning("Please enter email");
    } else if (password.length === 0) {
      toast.warning("Please enter password");
    } else {
      const body = {
        email,
        password,
      };

      // console.log(password);
      const url = `${URL}/user/signin`;

      axios.post(url, body).then((response) => {
        const result = response.data;
        // console.log(result);
        if (result["status"] === "success") {
          toast.success("Welcome to the showbuzz");

          const { user_id, first_name, last_name, role } = result["data"];

          sessionStorage["id"] = user_id;
          sessionStorage["firstName"] = first_name;
          sessionStorage["lastName"] = last_name;
          sessionStorage["loginStatus"] = 1;
          sessionStorage["role"] = role;
          if (result["data"].role == "user") {
            navigate("/city");
          } else if (result["data"].role === "admin") {
            navigate("/adminHome");
          } else {
            navigate("/managerHome");
          }
        } else {
          toast.error("Invalid user name or password");
        }
      });
    }
  };

  return (
    <div>
      <div className="row">
        <div className="col"></div>
        <div
          className="col  shadow p-3 mb-5 bg-body rounded"
          style={{ marginTop: "20px" }}
        >
          <h2 style={{ textAlign: "center" }}>Signin</h2>
          <hr />
          <div className="form">
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Email address
              </label>
              <input
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
                type="email"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Password
              </label>
              <input
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
                type="password"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <div>
                No account yet? <Link to="/signup">Signup here</Link>
              </div>
              <br />
              <div className="row">
                <button onClick={signinUser} className="btn btn-primary">
                  Signin
                </button>
              </div>
            </div>
          </div>
        </div>
        <div className="col"></div>
      </div>
    </div>
  );
};

export default Signin;
