import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import AdAllShowComp from "../../../components/AdminComp/AdAllShowComp";

function AdAllShow() {
  const [shows, setShows] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const url = "http://localhost:8080/show";
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setShows(result["data"]);
      } else {
        console.log(result["error"]);
      }
    });
  }, []);

  return (
    <div>
      <div className="container">
        <button
          onClick={() => {
            navigate("/addshow");
          }}
          type="button"
          class="btn btn-primary"
        >
          Add Show
        </button>

        <hr />

        {shows.map((s) => {
          return <AdAllShowComp show_detail={s} />;
        })}
      </div>
    </div>
  );
}

export default AdAllShow;
