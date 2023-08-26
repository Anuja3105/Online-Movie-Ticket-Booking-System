import { useEffect, useState } from "react";
import axios from "axios";
import Review from "../../../components/UserComp/Review";
import { useLocation } from "react-router";
import { useNavigate } from "react-router";
import { URL } from "../../../config";

function ReviewPage() {
  const [reviews, setReviews] = useState([]);
  const navigate = useNavigate();
  const { state } = useLocation();

  const movie_id = state.id;

  // console.log("movieId in ReviewPage is "+state.id);

  useEffect(() => {
    const url = `${URL}/review/` + movie_id;
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setReviews(result["data"]);
      } else {
        console.log(result["error"]);
      }
    });
  }, []);

  return (
    <div>
      <div className="row">
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
          <div class="d-grid gap-2 col-6 mx-auto"></div>
          <hr />
          <div class="d-grid gap-2 d-md-flex justify-content-md-end">
            <button
              onClick={() => {
                navigate("/addreview", { state: { id: movie_id } });
              }}
              type="button"
              class="btn btn-danger me-md-2"
            >
              Add Review
            </button>
          </div>
          <hr />
          <div>
            {reviews.map((s) => {
              return <Review review={s} />;
            })}
          </div>
        </div>

        <div className="col"></div>
      </div>
    </div>
  );
}

export default ReviewPage;
