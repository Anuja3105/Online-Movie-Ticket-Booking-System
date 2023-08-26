import axios from "axios";
import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import "./Modal.css";
import { URL } from "../config";
import { useNavigate } from "react-router-dom";

function Modal({ setOpenModal }) {
  const [city, setCity] = useState([]);
  const navigate = useNavigate();
  useEffect(() => {
    const url = `${URL}/city/all`;

    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] === "success") {
        setCity(result["data"]);
      } else {
        toast.warning(result["error"]);
      }
    });
  }, []);

  console.log(city);
  return (
    <div className="modalBackground">
      <div className="modalContainer">
        {/* <div className="titleCloseBtn">
          <button
            onClick={() => {
              setOpenModal(false);
            }}
          >
            X
          </button>
        </div>
        <div className="title">
          <h1>Are You Sure You Want to Continue?</h1>
        </div>
        <div className="body">
          <p>The next page looks amazing. Hope you want to go there!</p>
        </div>
        <div className="footer">
          <button
            onClick={() => {
              setOpenModal(false);
            }}
            id="cancelBtn"
          >
            Cancel
          </button>
          <button>Continue</button>
        </div> */}

        {/**=================================================================================================== */}

        {/* New Code */}

        <h2>Select City</h2>
        <hr />
        <div className="row">
          {city.map((c) => {
            return (
              <div>
                <div className="col"></div>
                <div className="col">
                  <button
                    className="btn btn-primary btn-lg btn-block"
                    onClick={() => {
                      navigate("/home", { state: { id: c.city_id } });
                      setOpenModal(false);
                    }}
                  >
                    {" "}
                    {c.city_name}{" "}
                  </button>
                </div>
                <div className="col"></div>
              </div>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default Modal;
