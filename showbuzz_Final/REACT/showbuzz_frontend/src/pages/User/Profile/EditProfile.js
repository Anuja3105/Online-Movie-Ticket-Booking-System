import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import { URL } from "../../../config";

const EditProfile = () => {
  const { state } = useLocation();
  const { user } = state;
  const id = user.user_id;
  const [contact, setContact] = useState("");
  const [address, setAddress] = useState("");
  const [city, setCity] = useState("");
  const [gender, setGender] = useState("");
  const [u_state, setUstate] = useState("");
  const [country, setCountry] = useState("");
  const [pincode, setPincode] = useState();

  console.log(user);
  const navigate = useNavigate();
  const save = () => {
    if (contact.length == 0) {
      toast.warning("please enter 10 digit contact number");
    } else if (contact.length != 10) {
      toast.warning("please enter valid contact number");
    } else if (address.length == 0) {
      toast.warning("please enter address");
    } else if (city.length == 0) {
      toast.warning("please enter city");
    } else if (u_state.length == 0) {
      toast.warning("please enter state");
    } else if (country.length == 0) {
      toast.warning("please enter country");
    } else if (pincode.length == 0) {
      toast.warning("please enter pincode");
    } else {
      const body = {
        phone_no: contact,
        address_line: address,
        city,
        state: u_state,
        country,
        pincode,
        gender,
      };
      const url = `${URL}/user/${id}`;
      axios.put(url, body).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          toast.success("successfully updated profile..");
          navigate("/profile");
        } else {
          toast.error(result["error"]);
        }
      });
    }
  };

  useEffect(() => {
    const { user } = state;
    setContact(user.phone_no);
    setAddress(user.address_line);
    setCity(user.city);
    setUstate(user.state);
    setCountry(user.country);
    setGender(user.gender);
    setPincode(user.pincode);
  }, []);
  return (
    <div>
      <h2 style={{ textAlign: "center" }}>Edit Profile</h2>
      <div className="container">
        <div className="row">
          <div className="col"></div>
          <div className="col shadow p-3 mb-5 bg-body rounded">
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                First Name :
              </label>
              <input
                className="form-control"
                type="text"
                value={user.first_name}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                Last Name :
              </label>
              <input
                className="form-control"
                type="text"
                value={user.last_name}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                E-Mail :
              </label>
              <input
                className="form-control"
                type="text"
                value={user.email}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                Contact Number :
              </label>
              <input
                className="form-control"
                type="text"
                value={contact}
                onChange={(e) => {
                  setContact(e.target.value);
                }}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                Gender :
              </label>
              <select
                className="form-control"
                type="button"
                onChange={(e) => {
                  setGender(e.target.value);
                }}
                value={gender}
              >
                <option>male</option>
                <option>female</option>
              </select>
            </div>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                Address :
              </label>
              <textarea
                onChange={(e) => {
                  setAddress(e.target.value);
                }}
                rows="3"
                className="form-control"
                value={address}
              ></textarea>
            </div>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                City :
              </label>
              <input
                className="form-control"
                type="text"
                value={city}
                onChange={(e) => {
                  setCity(e.target.value);
                }}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                State :
              </label>
              <input
                className="form-control"
                type="text"
                value={u_state}
                onChange={(e) => {
                  setUstate(e.target.value);
                }}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                Country :
              </label>
              <input
                value={country}
                className="form-control"
                type="text"
                onChange={(e) => {
                  setCountry(e.target.value);
                }}
              />
            </div>
            <div className="mb-3">
              <label htmlFor="" className="form-label">
                Pincode :
              </label>
              <input
                value={pincode}
                className="form-control"
                type="number"
                onChange={(e) => {
                  setPincode(e.target.value);
                }}
              />
            </div>
            <div className="mb-3">
              <button className="btn btn-primary float-start" onClick={save}>
                Update
              </button>
                
              <div className="mb-3">
            <button
          onClick={() => {
            navigate("/profile");
          }}
          type="button"
           class="btn btn-danger float-end"
        >
          Back
        </button>
        </div>
            </div>
          </div>
          <div className="col"></div>
        </div>
      </div>
      <hr />
    </div>
  );
};

export default EditProfile;
