import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import { URL } from "../../../config";
function EditTheatre() {
  const { state } = useLocation();
  console.log(state);
  const theatre_id = state.theatre.theatre_id;
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [address, setAddress] = useState("");

  function editTheatre() {
    const url = `${URL}/theatre/${theatre_id}`;
    if (name == "") {
      toast.error("enter city name");
    } else if (address == "") {
      toast.error("enter city state");
    } else {
      const body = {
        name: name,
        address: address,
      };

      axios.put(url, body).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          navigate("/adalltheatre");
          toast.success("Theatre updated");
        } else {
          toast.error("Error");
        }
      });
    }
  }

  useEffect(() => {
    const { theatre } = state;
    setAddress(theatre.address);
    setName(theatre.name);
  }, []);

  return (
    <div>
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <h1 className="title">Edit Theatre</h1>
          <hr />
          <div className="form">
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Name
              </label>
              <input
                value={name}
                onChange={(e) => {
                  setName(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Address :
              </label>
              <input
                value={address}
                onChange={(e) => {
                  setAddress(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <button onClick={editTheatre} className="btn btn-primary float-start">
                Submit
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

export default EditTheatre;
