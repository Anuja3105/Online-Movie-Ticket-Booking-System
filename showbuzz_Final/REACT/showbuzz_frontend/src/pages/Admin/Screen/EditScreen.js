import axios from "axios";
import { useState } from "react";
import { useLocation } from "react-router";
import { useNavigate } from "react-router";

function EditScreen(){

    const { state } = useLocation();
    const screen_id = state.id;
    const navigate = useNavigate();

    const [screen_no,setScreenNo] = useState(0);

    function editScreen(){
        const url = "http://localhost:8080/screen/"+screen_id;
        
            const body = {
               screen_no:screen_no,  
            }

            axios.put(url,body).then((response) =>{
                const result = response.data;
                if(result['status'] == 'success'){
                    navigate('/adallscreen');
                    console.log(result.data)
                }else{
                    console.log(result.error);
                }
            })
        
    }

    return (
        <div>
    
          <div className="row">
            <div className="col"></div>
            <div className="col">
            <h1 className="title">Edit Screen</h1>
            <hr />
              <div className="form">
               
              <div className="mb-3">
                  <label htmlFor="" className="label-control">
                  Screen No
                  </label>
                  <input onChange={(e) => {
                      setScreenNo(e.target.value)
                  }} type="number" className="form-control"/>
                </div>
    
               
                <div className="mb-3">
                  <button onClick={editScreen} className="btn btn-primary">
                    Submit
                  </button>
                </div>
              </div>
            </div>
            <div className="col"></div>
          </div>
        </div>
      )
}



export default EditScreen;