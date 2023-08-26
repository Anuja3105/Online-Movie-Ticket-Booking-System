import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router";

function AddScreen(){

    const navigate = useNavigate();


    const [screen_no,setScreenNo] = useState(0);
    const [theatre_id,SetTheatreId] = useState(0);


    function addScreen(){
        const url = "http://localhost:8080/screen";
            if(screen_no == 0){
                console.log('entry screen_no');
            }else if(theatre_id == 0){
                console.log('entry theatre_id');   
            }else{
                const body = {
                    screen_no:screen_no,
                    theatre_id:theatre_id,
                }
    
                axios.post(url,body).then((response) => {
                    const result = response.data;
                    if(result['status'] == 'success'){
                        navigate('/adallscreen');
                    }else{
                        console.log(result.error);
                    }
                })
            }
            
        }

        return (
            <div>
        
              <div className="row">
                <div className="col"></div>
                <div className="col">
                <h1 className="title">Add Screen</h1>
                <hr />
                  <div className="form">
    
                  <div className="mb-3">
                      <label htmlFor="" className="label-control">
                      Screen No:
                      </label>
                      <input onChange={(e) => {
                          setScreenNo(e.target.value)
                      }} type="number" className="form-control"/>
                    </div> 
                   
                    <div className="mb-3">
                      <label htmlFor="" className="label-control">
                      Theatre Id:
                      </label>
                      <input onChange={(e) => {
                          SetTheatreId(e.target.value)
                      }} type="number" className="form-control"/>
                    </div> 
    
                    <div className="mb-3">
                      <button onClick={addScreen} className="btn btn-success">
                        Add
                      </button>
                    </div>
                  </div>
                </div>
                <div className="col"></div>
              </div>
            </div>
          )

}



export default AddScreen;