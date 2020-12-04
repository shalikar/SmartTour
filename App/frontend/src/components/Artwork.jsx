import React, { useState } from "react";
import { Card } from "react-bootstrap";
import { Link } from "react-router-dom";

function Artwork(props) {
  return (
    <Card style={{ width: "15rem", margin: "auto" }}>
      <Card.Img variant="top" src={props.artwork.image} />
      <Card.Body>
        <Card.Title>{props.artwork.title}</Card.Title>
        <Card.Text style={{ fontSize: "14px", textAlign: "justify" }}>
          {props.artwork.text}
        </Card.Text>
        <Card.Link>
          <Link
            to={{
              pathname: "/details",
              state: {
                artwork: props.artwork,
              },
            }}
          >
            View more
          </Link>
        </Card.Link>
      </Card.Body>
    </Card>
  );
}

export default Artwork;
