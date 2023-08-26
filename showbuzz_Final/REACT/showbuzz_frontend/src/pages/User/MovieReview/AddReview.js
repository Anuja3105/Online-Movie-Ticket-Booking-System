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

function AddReview() {
  const [review, setReview] = useState("");
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

  function submitReview() {
    const url = `${URL}/review/` + movie_id;
    if (review.length == 0) {
      toast.warning("Enter the review !!");
    } else {
      const body = {
        user_id: id,
        review: review,
      };

      axios.post(url, body).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          toast.success("Rating added successfully...!!!");
          navigate("/allmovie");
        } else {
          toast.error("Error");
        }
      });
    }
  }

  return (
    <div>
      <div className="row"></div>
      <div className="row">
        <div className="col"></div>
        <div className="col  shadow p-3 mb-5 bg-body rounded ">
          <div
            class="movie review "
            style={{
              textAlign: "center",
              width: "400px",
              height: "auto",
              fontSize: "30px",
              fontWeight: "bold",
            }}
          >
            Add Movie Reviews
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
          <div class="mb-3">
            <div style={styles.title}>Review</div>
            <textarea
              onChange={(e) => {
                setReview(e.target.value);
              }}
              class="form-control"
              id="exampleFormControlTextarea1"
              rows="3"
            ></textarea>
          </div>
          <div className="row">
            <button
              onClick={submitReview}
              class="btn btn-primary btn-lg btn-block "
            >
              Submit
            </button>
          </div>
        </div>

        <div className="col"></div>
      </div>
      <div className="row"></div>
    </div>
  );
}

export default AddReview;
