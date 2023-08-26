import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router";
import { URL } from "../../config";

const styles = {
  title: {
    fontSize: "1.3em",
    fontWeight: "bold",
  },
  subtitle: {
    color: "darkgray",
    fontSize: "1.3em",
    fontWeight: "bold",
  },
};

function Rating(props) {
  const id = props.movie_detail.id;
  const [rating, setRating] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    const url = `${URL}/rating/` + id + "/aggregate";
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setRating(result["data"]);
      } else {
        console.log(result["error"]);
      }
    });
  }, []);

  return (
    <div>
      <div className="row">
        <div className="col"></div>
        <div className="col">
          <div>
            {/* <table>
              <tr>
                <td>
                  <div style={styles.title}>{rating.avg}</div>
                </td>
                <td>
                  <div style={styles.title}>..</div>
                </td>
                <td>
                  <div style={styles.subtitle}>ratings</div>
                </td>
                <td></td>
              </tr>
            </table> */}

            <button
              onClick={() => {
                navigate("/addrating", { state: { id: id } });
              }}
              type="button"
              class="btn btn-success"
            >
              Add Rating
            </button>
          </div>
        </div>
        <div className="col"></div>
      </div>
    </div>
  );
}

export default Rating;
