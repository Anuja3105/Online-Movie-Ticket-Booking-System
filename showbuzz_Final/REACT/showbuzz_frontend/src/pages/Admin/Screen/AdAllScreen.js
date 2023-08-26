import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router";
import AdAllScreenComp from "../../../components/AdminComp/AdAllScreenComp";

function AdAllScreen() {
  const [screens, setScreens] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const url = "http://localhost:8080/screen";
    axios.get(url).then((response) => {
      const result = response.data;
      if (result["status"] == "success") {
        setScreens(result["data"]);
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
            navigate("/addscreen");
          }}
          type="button"
          class="btn btn-primary"
        >
          Add Screen
        </button>

        <hr />

        {screens.map((s) => {
          return <AdAllScreenComp screen_detail={s} />;
        })}
      </div>
    </div>
  );
}

export default AdAllScreen;
