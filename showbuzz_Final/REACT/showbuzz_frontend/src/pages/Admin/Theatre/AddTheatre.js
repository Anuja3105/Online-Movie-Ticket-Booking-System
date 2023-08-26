import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import { URL } from "../../../config";
function AddTheatre() {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [city_id, setCityId] = useState(0);
  const [no_of_screen, setNoOfScreen] = useState(0);
  const [key1, setKey1] = useState("");
  const [key2, setKey2] = useState("");
  const [manager_id, setUserId] = useState(0);

  function addTheatre() {
    const url = `${URL}/theatre/add`;
    if (name == "") {
      toast.error("enter name");
    } else if (address == "") {
      toast.error("enter address");
    } else if (key1 == "") {
      toast.error("enter key1");
    } else if (key2 == "") {
      toast.error("enter key2");
    } else {
      const body = {
        name: name,
        address: address,
        city_id: city_id,
        no_of_screen: no_of_screen,
        key1: key1,
        key2: key2,
        manager_id: manager_id,
      };

      axios.post(url, body).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          navigate("/adalltheatre");
          toast.success(result.data);
        } else {
          toast.error(result.error);
        }
      });
    }
  }

  return (
    <div>
      <div className="row">
        <div className="col"></div>
        <div className="col shadow p-3 mb-5 bg-body rounded">
          <h2 className="title" style={{ textAlign: "center" }}>
            Add Theatre
          </h2>
          <hr />
          <div className="form">
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Theatre Name
              </label>
              <input
                onChange={(e) => {
                  setName(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Theatre Address
              </label>
              <input
                onChange={(e) => {
                  setAddress(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                City Id
              </label>
              <input
                onChange={(e) => {
                  setCityId(e.target.value);
                }}
                type="number"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                No of Screen
              </label>
              <input
                onChange={(e) => {
                  setNoOfScreen(e.target.value);
                }}
                type="number"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Key Id:
              </label>
              <input
                onChange={(e) => {
                  setKey1(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Secret Key:
              </label>
              <input
                onChange={(e) => {
                  setKey2(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Manager Id:
              </label>
              <input
                onChange={(e) => {
                  setUserId(e.target.value);
                }}
                type="number"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <button onClick={addTheatre} className="btn btn-success float-start">
                Add
              </button>

              <div className="mb-3">
            <button
          onClick={() => {
            navigate("/adalltheatre");
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

export default AddTheatre;
