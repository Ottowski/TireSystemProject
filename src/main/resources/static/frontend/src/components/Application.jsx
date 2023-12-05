import React from "react";
import NavBar from "../view/NavBar";
import { Outlet } from "react-router-dom";

export default function Application() {
    return(
    <div>
      <NavBar/>
          <Outlet/>
        </div>
    )
}
