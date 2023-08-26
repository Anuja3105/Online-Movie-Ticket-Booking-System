import React, { useEffect } from "react";
import { URL } from "../../../../config";
import axios from "axios";
import { useState } from "react";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import { useLocation, useNavigate } from "react-router";
import { Col, Container, Row, Button, Table, Form } from "reactstrap";

const MAddShow = () => {
  const navigate = useNavigate();
  const [screenId, setScreenId] = useState();
  const [movie, setMovie] = useState([]);
  const [movieName, setMovieName] = useState();
  const [costfactor, setCostFactor] = useState();
  const [showDate, setShowDate] = useState();
  const [showTime, setShowTime] = useState();
  const [movieList, SetMovieList] = useState([]);
  const [screenList, setScreenList] = useState([]);

  const AddShow = () => {
    if (movieName == null) {
      {
        toast.warning("Please Enter  MovieID");
      }
    } else if (screenId == null) {
      toast.warning("Please Enter  ScreenID");
    } else if (costfactor == 0) {
      toast.warning("Please Enter  CostFactor");
    } else if (showDate == null) {
      toast.warning("Please Enter  Showdate");
    } else if (showTime == null || showTime == "") {
      toast.warning("Please Enter  ShowTime");
    } else {
      const url2 = `${URL}/movie/name/${movieName}`;

      axios.get(url2).then((response2) => {
        const result2 = response2.data;
        if (result2["status"] == "success") {
          console.log(result2);
          const { id } = result2["data"];
          console.log(id);
          setMovie(result2["data"]);

          const body = {
            //   screen_no: screenNo,
            movie_id: movie[0].id,
            screen_id: screenId,
            cost_factor: costfactor,
            showdate: showDate,
            showtime: showTime,
          };

          const url = `${URL}/show`;
          axios.post(url, body).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
              navigate("/manageShows");
              toast.success("Show Updated");
            } else {
              toast.error("Error");
            }
          });
        }
      });
    }
  };

  const MovieList = () => {
    const url1 = `${URL}/movie/all`;
    axios.get(url1).then((response) => {
      const result1 = response.data;
      if (result1["status"] == "success") {
        console.log(result1);
        SetMovieList(result1["data"]);
      }
    });
  };

  const ScreenList = () => {
    const theatre_id = sessionStorage.getItem("theatreManagerId");
    console.log(theatre_id);
    const url = `${URL}/screen/${theatre_id}`;
    axios.get(url).then((response) => {
      const rseult = response.data;
      if (rseult["status"] == "success") {
        console.log(rseult);
        setScreenList(rseult["data"]);
      }
    });
  };

  useEffect(() => {
    MovieList();
    ScreenList();
  }, []);
  return (
    <div>
      <Container>
        <Row xs="3" style={{ marginTop: "40px" }}>
          {" "}
          <Col></Col>
          <Col className="shadow p-3 mb-5 bg-body rounded">
            <h2 style={{ textAlign: "center", marginTop: "20px" }}>
              Add New Show
            </h2>
            <hr />{" "}
            <div className="form">
              <div className="mb-3">
                <label htmlFor="for" className="label-form">
                  Select Movie Name
                </label>

                <select
                  className="form-select"
                  type="button"
                  onChange={(e) => {
                    setMovieName(e.target.value);
                  }}
                >
                  <option></option>
                  {movieList.map((movie) => {
                    return <option>{movie.movie_name}</option>;
                  })}
                </select>

                {/* <input
                  onChange={(e) => {
                    setMovieId(e.target.value);
                  }}
                  type="number"
                  className="form-control"
                  min="1"
                  placeholder={show.movie_id}
                  required 
                /> */}
              </div>
              <div className="mb-3">
                <label htmlFor="" className="label-form">
                  Select Screen Id
                </label>
                <select
                  className="form-select"
                  type="button"
                  onChange={(e) => {
                    setScreenId(e.target.value);
                  }}
                >
                  <option></option>
                  {screenList.map((screen) => {
                    console.log(screen);
                    return <option>{screen.id}</option>;
                  })}
                </select>
                {/* <input
                  onChange={(e) => {
                    setScreenId(e.target.value);
                  }}
                  type="number"
                  className="form-control"
                  min="1"
                  placeholder={show.screen_id}
                  required
                /> */}
              </div>
              <div className="mb-3">
                <label htmlFor="" className="label-control">
                  Enter Cost Factor
                </label>
                <input
                  onChange={(e) => {
                    setCostFactor(e.target.value);
                  }}
                  type="number"
                  className="form-control"
                  required
                />
              </div>
              <div className="mb-3">
                <label htmlFor="" className="label-control">
                  Enter Show Date
                </label>
                <input
                  onChange={(e) => {
                    setShowDate(e.target.value);
                  }}
                  type="date"
                  className="form-control"
                  min="1"
                  required
                />
              </div>
              <div className="mb-3">
                <label htmlFor="" className="label-control">
                  Enter Show Time
                </label>
                <input
                  onChange={(e) => {
                    setShowTime(e.target.value);
                  }}
                  type="text"
                  className="form-control"
                  required
                />
                Enter show time in HH:MM:SS format
              </div>
              <hr />
              <div className="mb-3">
                <button onClick={AddShow} className="btn btn-primary float-start">
                  Add Show
                </button>

                <div className="mb-3">
            <button
          onClick={() => {
            navigate("/manageShows");
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
export default MAddShow;
