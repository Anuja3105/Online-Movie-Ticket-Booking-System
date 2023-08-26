import axios from "axios";
import { useState } from "react";
import { useEffect } from "react";
import { useLocation } from "react-router";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import { URL } from "../../../config";

const styles = {
  title: {
    fontSize: "1.3em",
    fontWeight: "bold",
    textAlign: "center",
  },
  subtitle: {
    color: "darkgray",
  },
  detailsContainer: {
    marginTop: "10px",
    marginBottom: "10px",
  },
};
function AddRating() {
  const [rating, setRating] = useState(0);
  const [movie, setMovie] = useState("");
  const navigate = useNavigate();
  const { state } = useLocation();
  const movie_id = state.id;
  const { id } = sessionStorage;
  useEffect(() => {
    const url = `${URL}/movie/find/` + movie_id;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setMovie(result.data);
      }
    });
  }, []);

  function submitRating() {
    const url = `${URL}/rating/` + movie_id;

    const body = {
      user_id: id,
      rating: rating,
    };

    axios.post(url, body).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        toast.success("Rating added successfully...!!!");
        navigate("/allmovie");
      } else {
        toast.warning("Error");
      }
    });
  }

  return (
    <div>
      <div className="row"></div>
      <div className="row ">
        <div className="col"></div>
        <div className="col shadow p-3 mb-5 bg-body rounded">
          <div
            class="movie review"
            style={{
              textAlign: "center",
              width: "400px",
              height: "auto",
              fontSize: "30px",
              fontWeight: "bold",
            }}
          >
            Movie Reviews
          </div>
          <hr />
          <div style={styles.title}>Movie : {movie.movie_name}</div>

          <div>
            <img
              src={movie.moviePoster}
              className="img-fluid img-thumbnail rounded mx-auto d-block"
              alt="movie poster"
              width={200}
            />
          </div>
          <br />
          <hr />
          <div>
            <div
              class="row g-3 align-items-center"
              style={{ textAlign: "center" }}
            >
              <div class="col-auto">
                <div style={styles.title}>Rating :</div>
              </div>
              <div class="col-auto">
                <input
                  onChange={(e) => {
                    setRating(e.target.value);
                  }}
                  type="number"
                  class="form-control"
                  aria-describedby="passwordHelpInline"
                />
              </div>
              <div class="col-auto">
                <span id="passwordHelpInline" class="form-text">
                  Must be in betwwen 0.0-5.0 .
                </span>
              </div>
              <button onClick={submitRating} class="btn btn-primary">
                Add
              </button>
            </div>
            <hr />
          </div>
        </div>

        <div className="col"></div>
      </div>
      <div className="row"></div>
    </div>
  );
}

export default AddRating;
