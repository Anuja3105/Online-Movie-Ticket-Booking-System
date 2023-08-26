import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router";
import { toast } from "react-toastify";
import { URL } from "../../config";
function AddMovie() {
  const navigate = useNavigate();

  const [movie_name, setMovieName] = useState("");
  const [movie_details, setMovieDetail] = useState("");
  const [category, setCategory] = useState("");
  const [genre, setGenre] = useState("");
  const [status, setStatus] = useState(1);
  const [release_date, setReleaseDat] = useState("");
  const [language, setLangauge] = useState("");
  const [movie_poster, setMoviePoster] = useState("");

  function addMovie() {
    const url = `${URL}/movie`;
    if (movie_details == "") {
      toast.warning("Enter movie detail");
    } else if (category == "") {
      toast.warning("Enter category");
    } else if (genre == "") {
      toast.warning("Enter genre");
    } else if (movie_name == "") {
      toast.warning("Enter movie name");
    } else if (release_date == "") {
      toast.warning("Enter release date");
    } else if (language == "") {
      toast.warning("Enter langauage");
    } else if (movie_poster == "") {
      toast.warning("Add movie poster");
    } else {
      const body = {
        movie_name: movie_name,
        movie_details: movie_details,
        genre: genre,
        status: status,
        language: language,
        release_date: release_date,
        moviePoster: movie_poster,
        category: category,
      };

      axios.post(url, body).then((response) => {
        const result = response.data;
        if (result["status"] == "success") {
          navigate("/adallmovie");
          toast.success("Movie added successfully!!!");
        } else {
          toast.warning("Error...!!!");
        }
      });
    }
  }

  return (
    <div>
      <div className="row" style={{ marginTop: "1em" }}>
        <div className="col"></div>
        <div className="col shadow p-3 mb-5 bg-white rounded">
          <h2 className="title" style={{ textAlign: "center" }}>
            Add Movie
          </h2>
          <hr />
          <div className="form">
            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Movie Name
              </label>
              <input
                onChange={(e) => {
                  setMovieName(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Movie Details
              </label>
              <textarea
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
                onChange={(e) => {
                  setGenre(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Movie poster
              </label>
              <input
                onChange={(e) => {
                  setMoviePoster(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Language
              </label>
              <input
                onChange={(e) => {
                  setLangauge(e.target.value);
                }}
                type="text"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <label htmlFor="" className="label-control">
                Release Date
              </label>
              <input
                onChange={(e) => {
                  setReleaseDat(e.target.value);
                }}
                type="date"
                className="form-control"
              />
            </div>

            <div className="mb-3">
              <button onClick={addMovie} className="btn btn-success float-start">
                Add
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

export default AddMovie;
