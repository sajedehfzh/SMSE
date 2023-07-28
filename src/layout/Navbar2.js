import React from 'react'
import { Link } from 'react-router-dom'

export default function Navbar() {
  return (
    <div>
<nav class="navbar  navbar-expand-lg navbar-light bg-dark">
  <div className="container-fluid">
    <a className="navbar-brand " href="https://localhost:3000/" style={{ color: "white" }}>Score Management Software Engineering (SMSE) </a>
    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <Link className="btn btn-outline-light" to="/addResult">Add result</Link>
    <div style={{ flexGrow: 1 }}></div>
      <ul style={{ display: "flex", listStyle: "none", margin: 0, padding: 0 }}>
        <li style={{ margin: "0 10px" }}>
          <a href="#">Report Results</a>
        </li>
        <li style={{ margin: "0 10px" }}>
          <a href="#">Edit Result</a>
        </li>
        <li style={{ margin: "0 10px" }}>
          <a href="#">Add Result</a>
          <ul
            style={{
              backgroundColor: "white",
              border: "1px solid gray",
              position: "absolute",
              zIndex: 1,
              margin: 0,
              padding: "10px",
              textAlign: "left",
            }}
          >
            <li>
              <a href="#">Add Manually</a>
            </li>
            <li>
              <a href="#">Add with Excel</a>
            </li>
          </ul>
        </li>
      </ul>
  </div>
</nav>

    </div>
  )
}
