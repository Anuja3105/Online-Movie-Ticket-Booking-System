import { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import "./index.css";
import { toast } from "react-toastify";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { URL } from "../../../config";
import { Container, Row, Col } from "reactstrap";
const Home = () => {
  const { id, firstName, lastName, city_name } = sessionStorage;
  const navigate = useNavigate();
  const [movie, setMovie] = useState([]);
  var currentDate = new Date();
  var displayDate = currentDate.toDateString();

  const logoutUser = () => {
    sessionStorage.removeItem("id");
    sessionStorage.removeItem("firstName");
    sessionStorage.removeItem("lastName");
    sessionStorage.removeItem("loginStatus");
    sessionStorage.removeItem("role");
    sessionStorage.removeItem("movie_name");
    sessionStorage.removeItem("movie_id");
    sessionStorage.removeItem("movie_poster");
    sessionStorage.removeItem("booking_date");
    sessionStorage.removeItem("booking_time");
    sessionStorage.removeItem("screen_no");
    sessionStorage.removeItem("theatre_name");
    sessionStorage.removeItem("theatre_address");
    sessionStorage.removeItem("cost_factor");
    sessionStorage.removeItem("checkedSeats");
    sessionStorage.removeItem("selectedSeats");
    sessionStorage.removeItem("totalCost");
    sessionStorage.removeItem("city_id");
    sessionStorage.removeItem("city_name");

    navigate("/");
  };

  // const cityId = 1;
  const { state } = useLocation();
  const cityId = state.id;
  const loadMovieDetails = () => {
    const url = `${URL}/movie/city/${cityId}`;
    // const url = `${URL}/movie/all`;
    axios.get(url).then((response) => {
      const result = response.data;
      console.log("result=========",result);
      if (result["status"] == "success") {
        setMovie(result["data"]);
      } else {
        toast.warning(result["error"]);
      }
    });
  };

  useEffect(() => {
    loadMovieDetails();
  }, []);

  console.log(movie);
  return (
    <div>
      <div className="row">
        <div className="col "></div>
        <hr />

        <div className="col">
          <div className="float-start">
            <div className="time" style={{ boxShadow: "4px 4px 5px gray" }}>
              {displayDate}
            </div>
          </div>
          <div className="float-start">
            <div
              className="time"
              style={{ marginLeft: "1em", boxShadow: "4px 4px 5px gray" }}
              type="button"
              onClick={() => {
                navigate("/allmovie");
              }}
            >
              All Movies
            </div>
          </div>
          <div className="float-start">
            <div
              className="time"
              style={{
                marginLeft: "1em",
                width: "8em",
                textAlign: "center",
                boxShadow: "4px 4px 5px gray",
              }}
              type="button"
              onClick={() => {
                navigate("/city");
              }}
            >
              {city_name}
            </div>
          </div>
          <div className="float-end">
            <div
              className="btn-group"
              role="group"
              style={{ boxShadow: "4px 4px 5px gray" }}
            >
              <button
                id="btnGroupDrop1"
                type="button"
                className="btn btn-primary dropdown-toggle"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Welcome {firstName}
              </button>
              <ul className="dropdown-menu" aria-labelledby="btnGroupDrop1">
                <li>
                  <button
                    onClick={() => {
                      navigate("/profile", { state: { id: id } });
                    }}
                    className="dropdown-item"
                  >
                    Profile
                  </button>
                </li>
                <li>
                  <button
                    onClick={() => {
                      navigate("/myOrders", { state: { id: id } });
                    }}
                    className="dropdown-item"
                  >
                    My Orders
                  </button>
                </li>
                <li>
                  <button onClick={logoutUser} className="dropdown-item">
                    Logout
                  </button>
                </li>
              </ul>
            </div>
          </div>
        </div>

        <hr />
      </div>

      {/* *********************************************************************************** */}
      {/* <div className="row">
        <button
          className="btn btn-primary"
          onClick={() => {
            navigate("/show", { state: { id: 1 } });
          }}
        >
          Select Movie
        </button>
      </div> */}
      <div>
        {/* <img
          src={require("../../components/static/images/pushpa_poster.jpg")}
          alt=""
        /> */}
        {/* <div className="container">
          <div className="row">
            {movie.map((m) => {
              return (
                <div key={m?.movie_id}>
                  <div className="col md-2">
                    <img
                      className="btn"
                      src={m?.moviePoster}
                      alt="movie_poster"
                      width={200}
                      onClick={() => {
                        sessionStorage["movie_name"] = m.movie_name;
                        sessionStorage["movie_id"] = m.id;
                        sessionStorage["movie_poster"] = m.moviePoster;
                        navigate("/show", { state: { id: m.id } });
                      }}
                    />
                  </div>
                </div>
              );
            })}
          </div>
        </div> */}

        <Container>
          <Row xs={1} md={4}>
            {movie.map((m) => {
              return (
                <div key={m?.movie_id}>
                  <div className="col md-2 shadow p-3 mb-5 bg-body rounded">
                    <img
                      className="btn rounded mx-auto d-block"
                      src={m?.moviePoster}
                      alt="movie_poster"
                      width={250}
                      onClick={() => {
                        sessionStorage["movie_name"] = m.movie_name;
                        sessionStorage["movie_id"] = m.id;
                        sessionStorage["movie_poster"] = m.moviePoster;
                        navigate("/show", { state: { id: m.id } });
                      }}
                    />
                    <h6 style={{ textAlign: "center" }}>
                      {m.language.toUpperCase()}
                    </h6>
                  </div>
                </div>
              );
            })}
          </Row>
        </Container>
      </div>
    </div>
  );
};

export default Home;
