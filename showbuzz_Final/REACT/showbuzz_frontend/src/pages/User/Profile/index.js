import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
// import "./index.css";
import { toast } from "react-toastify";
import axios from "axios";
import { URL } from "../../../config";

const Profile = (props) => {
  const { state } = useLocation;
  const [user, setUser] = useState({});
  const userId = sessionStorage["id"];
  const navigate = useNavigate();
  const { city_id, role } = sessionStorage;
  // console.log(userId);
  const loadUserProfile = () => {
    const url = `${URL}/user/${userId}`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setUser(result["data"]);
      }
    });
  };

  // console.log(user.data.first_name);
  useEffect(() => {
    loadUserProfile();
  }, []);

  // console.log(user.email);
  // console.log(user);
  return (
    <div>
      <h2 style={{ textAlign: "center" }}>Profile</h2>
      <hr />
      <button
        className="btn btn-danger"
        onClick={() => {
          if (role == "user") {
            navigate("/home", { state: { id: city_id } });
          } else {
            navigate("/managerHome");
          }
        }}
      >
        Back
      </button>

      <hr />
      {/* 
      phone_no
      address_line
      city
      state
      country
      pincode
      gender
      role 
      */}
      <div className="row">
        <div className="col"></div>
        <div className="col shadow p-3 mb-5 bg-body rounded">
          <div className="mb-3">
            <label htmlFor="" className="label-control">
              First Name :
            </label>
            {user.first_name}
          </div>

          <div className="mb-3">
            <label htmlFor="" className="label-control">
              Last Name :
            </label>

            {user.last_name}
          </div>

          <div className="mb-3">
            <label htmlFor="" className="label-control">
              Email Address :
            </label>
            {user.email}
          </div>
          <div className="mb-3">
            <label htmlFor="" className="label-control">
              Contact Number :
            </label>
            {user.phone_no}
          </div>
          <div className="mb-3">
            <label htmlFor="" className="label-control">
              Address :
            </label>
            {user.address_line}
          </div>
          <div className="mb-3">
            <label htmlFor="" className="label-control">
              City :
            </label>
            {user.city}
          </div>
          <div className="mb-3">
            <label htmlFor="" className="label-control">
              State :
            </label>
            {user.state}
          </div>
          <div className="mb-3">
            <label htmlFor="" className="label-control">
              Country :
            </label>
            {user.country}
          </div>
          <div className="mb-3">
            <label htmlFor="" className="label-control">
              Pincode :
            </label>
            {user.pincode}
          </div>

          <hr />
          <button
            type="button"
            className="btn btn-primary"
            onClick={() => {
              navigate("/editProfile", { state: { user: user } });
            }}
          >
            Edit
          </button>
        </div>

        <div className="col"></div>
      </div>
    </div>
  );
};

export default Profile;
