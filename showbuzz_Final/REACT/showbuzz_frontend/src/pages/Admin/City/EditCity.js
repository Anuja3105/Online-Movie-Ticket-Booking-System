import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import { URL } from "../../../config";
function EditCity() {
  const { state } = useLocation();
  const { city_detail } = state;
  const city_id = state.city_detail.city_id;
  const navigate = useNavigate();

  const [city_name, setCityName] = useState("");
  const [city_state, setCityState] = useState("");
  const [city_pincode, setCityPincode] = useState(0);

  function editCity() {
    const url = `${URL}/city/${city_id}`;
    if (city_name == "") {
      toast.error("enter city name");
    } else if (city_state == "") {
      toast.error("enter state");
    } else if (city_pincode == 0) {
      toast.error("enter city pincode");
    } else {
      const body = {
        city_name: city_name,
        city_state: city_state,
        city_pincode: city_pincode,
      };

      axios.put(url, body).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          navigate("/adallcity");
          toast.success("City details updated successfully...!!!");
        } else {
          toast.error(result.error);
        }
      });
    }
  }
  useEffect(() => {
    const { city_detail } = state;
    setCityName(city_detail.city_name);
    setCityState(city_detail.city_state);
    setCityPincode(city_detail.city_pincode);
  }, []);
  return (
    <div>
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <h1 className="title">Edit City</h1>
          <hr />
          <div className="form">
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                City Name
              </label>
              <input
                value={city_name}
                onChange={(e) => {
                  setCityName(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                City State
              </label>
              <input
                value={city_state}
                onChange={(e) => {
                  setCityState(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                City Pincode
              </label>
              <input
                value={city_pincode}
                onChange={(e) => {
                  setCityPincode(e.target.value);
                }}
                type="number"
                className="form-control"
              />
            </div>
            <div className="mb-3">
              <button onClick={editCity} className="btn btn-primary float-start">
                Submit
              </button>

              <div className="mb-3">
            <button
          onClick={() => {
            navigate("/adallcity");
          }}
          type="button"
           class="btn btn-danger float-end"
        >
          Back
        </button>
        </div>
            </div>
            
          </div>
          
        </div>
        <div className="col"></div>
      </div>
    </div>
  );
}

export default EditCity;
