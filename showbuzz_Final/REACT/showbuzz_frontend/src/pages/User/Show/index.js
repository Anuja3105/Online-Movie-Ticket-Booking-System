import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
// import "./index.css";
import { toast } from "react-toastify";
import axios from "axios";
import { URL } from "../../../config";
import TheatreList from "../../../components/theatre";

const Show = (props) => {
  const { state } = useLocation();
  const [shows, setShows] = useState([]);
  const [theatres, setTheatres] = useState([]);
  const [movies, setMovies] = useState({});
  const navigate = useNavigate();
  const { movie_id, city_id } = sessionStorage;

  const loadShowDetails = () => {
    const { id } = state;
    // const id = 1;
    const url = `${URL}/show/movie/${id}`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setShows(result["data"]);
      } else {
        toast.error(result["error"]);
      }
    });
  };

  const loadMovieDetails = () => {
    const id = state;
    // const id = 1;
    const url = `${URL}/movie/find/${id}`;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setMovies(result["data"]);
      } else {
        toast.error(result["error"]);
      }
    });
  };

  const loadTheatreDetails = (date) => {
    // const { id } = state;
    const id = 1;
    const url = `${URL}/theatre/movie?movie_id=` + id + `&booking_date=` + date;

    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setTheatres(result["data"]);
      } else {
        toast.error(result["error"]);
      }
    });
  };

  useEffect(() => {
    // loadShowDetails();
    // loadTheatreDetails();
    // loadMovieDetails();
  }, []);

  // console.log(movies);

  var current_date = new Date();
  var bookingDate =
    current_date.getFullYear() +
    "-" +
    (current_date.getMonth() + 1) +
    "-" +
    current_date.getDate();

  console.log(bookingDate);
  var next_date = new Date();
  next_date.setDate(new Date().getDate() + 1);
  var nextBookingDate =
    next_date.getFullYear() +
    "-" +
    (next_date.getMonth() + 1) +
    "-" +
    next_date.getDate();

  var next_next_date = new Date();
  next_next_date.setDate(new Date().getDate() + 2);

  var nextNextBookingDate =
    next_next_date.getFullYear() +
    "-" +
    (next_next_date.getMonth() + 1) +
    "-" +
    next_next_date.getDate();
  // var currentDate = current_date.toISOString().split("T")[0];
  // var nextDate = next_date.toISOString().split("T")[0];
  // var nextNextDate = next_next_date.toISOString().split("T")[0];
  // console.log(currentDate);
  // console.log(current_date.getDate());
  return (
    <div>
      <h2 style={{ textAlign: "center" }}>Show Details</h2>
      <hr />
      <button
        className="btn btn-danger"
        onClick={() => {
          navigate("/home", { state: { id: city_id } });
          sessionStorage.removeItem("movie_id");
          sessionStorage.removeItem("movie_name");
          sessionStorage.removeItem("movie_poster");
        }}
      >
        Back
      </button>
      <hr />
      <div>
        <h2> {movies?.movie_name} </h2>
      </div>

      <hr />

      <button
        className="btn btn-danger"
        style={{ marginLeft: "1em" }}
        onClick={() => {
          // const id = 1;
          sessionStorage["booking_date"] = bookingDate;
          const url =
            `${URL}/theatre/movie?movie_id=` +
            movie_id +
            `&booking_date=` +
            bookingDate +
            `&city_id=` +
            city_id;

          axios.get(url).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
              setTheatres(result["data"]);
            } else {
              toast.error(result["error"]);
            }
          });
        }}
      >
        {bookingDate}
      </button>

      <button
        className="btn btn-danger"
        style={{ marginLeft: "1em" }}
        onClick={() => {
          // const id = 1;
          sessionStorage["booking_date"] = nextBookingDate;

          const url =
            `${URL}/theatre/movie?movie_id=` +
            movie_id +
            `&booking_date=` +
            nextBookingDate +
            `&city_id=` +
            city_id;

          axios.get(url).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
              setTheatres(result["data"]);
            } else {
              toast.error(result["error"]);
            }
          });
        }}
      >
        {nextBookingDate}
      </button>
      <button
        className="btn btn-danger"
        style={{ marginLeft: "1em" }}
        onClick={() => {
          // const id = 1;
          sessionStorage["booking_date"] = nextNextBookingDate;

          const url =
            `${URL}/theatre/movie?movie_id=` +
            movie_id +
            `&booking_date=` +
            nextNextBookingDate +
            `&city_id=` +
            city_id;

          axios.get(url).then((response) => {
            const result = response.data;
            if (result["status"] == "success") {
              setTheatres(result["data"]);
            } else {
              toast.error(result["error"]);
            }
          });
        }}
      >
        {nextNextBookingDate}
      </button>

      <div className="row" style={{ marginTop: "20px", marginBottom: "20px" }}>
        <div className="col">
          {theatres.map((theatre) => {
            return <TheatreList theatre={theatre} />;
          })}
        </div>
      </div>
      {/* <div className="row" style={{ marginTop: "20px", marginBottom: "20px" }}>
        <div className="col">
          {shows.map((show) => {
            return <ShowList show={show} />;
          })}
        </div>
      </div> */}
    </div>
  );
};

export default Show;
