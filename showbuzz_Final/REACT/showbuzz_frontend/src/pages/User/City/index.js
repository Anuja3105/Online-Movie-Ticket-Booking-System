import axios from "axios";
import React, { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { URL } from "../../../config";
import { useNavigate } from "react-router-dom";
import "./index.css";

function City({}) {
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
    <div>
      {/**=================================================================================================== */}

      {/* New Code */}
      <div className="row">
        <div className="col"></div>
        <div className="col"></div>
        <div className="col">
          <div
            class="movie review "
            style={{
              textAlign: "center",
              width: "400px",
              height: "auto",
              fontSize: "30px",
              fontWeight: "bold",
              boxShadow: "2px 2px 6px gray",
            }}
          >
            Select City
          </div>
        </div>
        <div className="col"></div>
        <div className="col"></div>
      </div>
      <hr />
      {city.map((c) => {
        return (
          <div>
            <div className="row">
              <div className="col"></div>
              <div className="col"></div>
              <div className="col">
                <div
                  className="city"
                  type="button"
                  onClick={() => {
                    navigate("/home", { state: { id: c.city_id } });
                    sessionStorage["city_id"] = c.city_id;
                    sessionStorage["city_name"] = c.city_name;
                  }}
                >
                  {" "}
                  {c.city_name}{" "}
                </div>
              </div>
              <div className="col"></div>
              <div className="col"></div>
            </div>
          </div>
        );
      })}
    </div>
  );
}

export default City;
