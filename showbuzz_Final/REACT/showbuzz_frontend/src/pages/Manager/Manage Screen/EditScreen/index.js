import React, { useEffect } from "react";
import { URL } from "../../../../config";
import axios from "axios";
import { useState } from "react";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import { useLocation, useNavigate } from "react-router";
import { Col, Container, Row, Button, Table, Form } from "reactstrap";

const MEditScreen = () => {
  const { state } = useLocation();
  const screen_id = state.id;
  const navigate = useNavigate();
  const [screenNo, setScreenNo] = useState();
  console.log(screen_id);
  const editScreenNo = () => {
    const url = `${URL}/screen/${screen_id}`;
    const body = {
      screen_no: screenNo,
    };

    axios.put(url, body).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        navigate("/manageScreens");
        toast.success("Screen Updated");
      } else {
        toast.error("Error");
      }
    });
  };

  return (
    <div>
      <Container>
        <h2 style={{ textAlign: "center", marginTop: "20px" }}>
          Edit Screen No
        </h2>
        <hr />{" "}
        <Row xs="3" style={{ marginTop: "40px" }}>
          {" "}
          <Col></Col>
          <Col className="shadow p-3 mb-5 bg-body rounded">
            <div className="form">
              <div className="mb-3">
                <label htmlFor="" className="label-control">
                  Screen Id
                </label>
                <input
                  type="number"
                  className="form-control"
                  min="1"
                  value={screen_id}
                  readOnly="true"
                />
              </div>
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
                  value={screenNo}
                />
              </div>
              <hr />
              <div className="mb-3">
                <button onClick={editScreenNo} className="btn btn-primary float-start">
                  Update
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
export default MEditScreen;
