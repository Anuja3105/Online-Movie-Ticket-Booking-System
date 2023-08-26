import React, { useEffect } from "react";
import { URL } from "../../../../config";
import axios from "axios";
import { useState } from "react";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import { useLocation, useNavigate } from "react-router";
import { Col, Container, Row, Button, Table, Form } from "reactstrap";

const MAddScreen = () => {
  const theatre_id = sessionStorage.getItem("theatreManagerId");
  console.log(theatre_id);
  const [screen, setScreenNo] = useState();
  const navigate = useNavigate();

  const addScreenNo = () => {
    const url = `${URL}/screen`;
    const body = {
      screen_no: screen,
      theatre_id: theatre_id,
    };
    axios.post(url, body).then((response) => {
      const result = response.data;
      console.log(result);
      if (result["status"] == "success") {
        toast.success("Screen Added");
        navigate("/manageScreens");
      } else {
        toast.error("Screen No Already Present");
      }
    });
  };

  return (
    <div>
      <Container style={{ textAlign: "center", marginTop: "30px" }}>
        <Row xs="3" style={{ marginTop: "40px" }}>
          {" "}
          <Col></Col>
          <Col className="shadow p-3 mb-5 bg-body rounded">
            <h2> Add New Screen</h2>
            <hr />
            <div className="form">
              <div className="mb-3"></div>
              <div className="mb-3">
                <label htmlFor="" className="label-control">
                  Enter Screen No
                </label>
                <input
                  onChange={(e) => {
                    setScreenNo(e.target.value);
                  }}
                  type="number"
                  className="form-control"
                  min="1"
                  value={screen}
                />
              </div>
              <hr />
              <div className="mb-3">
                <button onClick={addScreenNo} className="btn btn-primary float-start">
                  Add Screen
                </button>

                <div className="mb-3">
            <button
          onClick={() => {
            navigate("/manageScreens");
          }}
          type="button"
           class="btn btn-danger float-end"
        >
          Back
        </button>
        </div>
              </div>
            </div>
          </Col>
          <Col></Col>
        </Row>
      </Container>
    </div>
  );
};
export default MAddScreen;
