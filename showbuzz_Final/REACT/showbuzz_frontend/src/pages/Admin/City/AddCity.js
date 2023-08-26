import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import { URL } from "../../../config";

function AddCity() {
  const navigate = useNavigate();

  const [city_name, setCityName] = useState("");
  const [city_state, setCityState] = useState("");
  const [city_pincode, setCityPincode] = useState(0);

  function addcity() {
    const url = `${URL}/city`;
    if (city_name == "") {
      toast.error("entry city_name");
    } else if (city_state == "") {
      toast.error("city_state");
    } else if (city_pincode == 0) {
      toast.error("entry city_pincode");
    } else {
      const body = {
        city_name: city_name,
        city_state: city_state,
        city_pincode: city_pincode,
      };

      axios.post(url, body).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          navigate("/adallcity");
          toast.success("City added...!!!");
        } else {
          console.log(result.error);
        }
      });
    }
  }

  return (
    <div>
      <div className="row " style={{ marginTop: "1em" }}>
        <div className="col"></div>
        <div className="col shadow p-3 mb-5 bg-white rounded">
          <h2 className="title" style={{ textAlign: "center" }}>
            Add City
          </h2>
          <hr />
          <div className="form">
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                City Name
              </label>
              <input
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
                onChange={(e) => {
                  setCityState(e.target.value);
                }}
                class="form-control"
                id="exampleFormControlTextarea1"
                rows="3"
              ></input>
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                City Pincode
              </label>
              <input
                onChange={(e) => {
                  setCityPincode(e.target.value);
                }}
                type="number"
                className="form-control"
              />
            </div>
            <hr />
            {/* <div className="row">
              <button onClick={addcity} className="btn btn-success float-start">
                Add
              </button>


            </div> */}
            
            <div className="mb-3">
              <button onClick={addcity} className="btn btn-success float-start">
                Add
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
export default AddCity;
