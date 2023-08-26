import { useState } from "react";
import { Link } from "react-router-dom";
import "./index.css";
import axios from "axios";
import { toast } from "react-toastify";
import { useNavigate } from "react-router";
import { URL } from "../../config";

const Signup = () => {
  const [first_name, setFirstName] = useState("");
  const [last_name, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const navigate = useNavigate();

  const signupUser = () => {
    if (first_name.length === 0) {
      toast.warning("Please enter first name");
    } else if (last_name.length === 0) {
      toast.warning("Please enter first name");
    } else if (email.length === 0) {
      toast.warning("Please enter email");
    } else if (password.length === 0) {
      toast.warning("Please enter password");
    } else if (confirmPassword.length === 0) {
      toast.warning("Please enter password");
    } else if (password !== confirmPassword) {
      toast.warning("Password does not match");
    } else {
      const body = {
        first_name,
        last_name,
        email,
        password,
      };

      const url = `${URL}/user/signup`;

      axios.post(url, body).then((response) => {
        const result = response.data;
        console.log(result);
        if (result["status"] === "success") {
          toast.success(
            "Successfully signed up. Welcome to the showbuzz familly..!!"
          );
          navigate("/");
        } else {
          toast.error(result["error"]);
        }
      });
    }
  };

  return (
    <div>
      <div className="row">
        <div className="col"></div>
        <div
          className="col shadow p-3 mb-5 bg-body rounded"
          style={{ marginTop: "1em" }}
        >
          <h2 className="title" style={{ textAlign: "center" }}>
            Signup
          </h2>
          <hr />
          <div className="form">
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                First Name
              </label>
              <input
                onChange={(e) => {
                  setFirstName(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Last Name
              </label>
              <input
                onChange={(e) => {
                  setLastName(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>
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
              <label htmlFor="" className="label-control">
                Confirm Password
              </label>
              <input
                onChange={(e) => {
                  setConfirmPassword(e.target.value);
                }}
                type="password"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <div>
                Already have an account ? <Link to="/">Signin here</Link>
              </div>
              <button onClick={signupUser} className="btn btn-primary">
                Signup
              </button>
            </div>
          </div>
        </div>
        <div className="col"></div>
      </div>
    </div>
  );
};

export default Signup;
