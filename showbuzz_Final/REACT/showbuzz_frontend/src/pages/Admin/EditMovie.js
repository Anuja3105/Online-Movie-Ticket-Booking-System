import axios from "axios";
import { useEffect, useState } from "react";
import { useLocation } from "react-router";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import { URL } from "../../config";
function EditMovie() {
  const { state } = useLocation();
  console.log(state);
  const movie_id = state.movie.id;
  const navigate = useNavigate();
  const { movie } = state;
  const [movie_details, setMovieDetail] = useState("");
  const [category, setCategory] = useState("");
  const [genre, setGenre] = useState("");
  const [status, setStatus] = useState(1);

  function editMovie() {
    const url = `${URL}/movie/${movie_id}`;

    if (movie_details == "") {
      console.log("enter movie detail");
    } else if (category == "") {
      console.log("enter Category");
    } else if (genre == "") {
      console.log("enter Genre");
    } else {
      const body = {
        movie_details: movie_details,
        category: category,
        genre: genre,
        status: status,
      };

      axios.put(url, body).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          toast.success("Details updated successfully...!!!");
          navigate("/adallmovie");
        } else {
          console.log(result.error);
        }
      });
    }
  }

  useEffect(() => {
    const { movie } = state;
    setCategory(movie.category);
    setGenre(movie.genre);
    setMovieDetail(movie.movie_details);
    setStatus(movie.status);
  }, []);

  return (
    <div>
      <div className="row" style={{ marginTop: "1em" }}>
        <div className="col"></div>
        <div className="col shadow p-3 mb-5 bg-body rounded">
          <h2 className="title" style={{ textAlign: "center" }}>
            Edit Movie
          </h2>
          <hr />
          <div className="form">
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Movie Details
              </label>
              <textarea
                value={movie_details}
                onChange={(e) => {
                  setMovieDetail(e.target.value);
                }}
                class="form-control"
                id="exampleFormControlTextarea1"
                rows="3"
              ></textarea>
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Category
              </label>
              <input
                value={category}
                onChange={(e) => {
                  setCategory(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Status
              </label>
              <input
                value={status}
                onChange={(e) => {
                  setStatus(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Genre
              </label>
              <input
                value={genre}
                onChange={(e) => {
                  setGenre(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <button onClick={editMovie}  class="btn btn-success float-start">
                Submit
              </button>

              <div className="mb-3">
            <button
          onClick={() => {
            navigate("/adallmovie");
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

export default EditMovie;
