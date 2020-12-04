import React from "react";
import { Row, Col } from "react-bootstrap";

import Artwork from "./Artwork";

function ArtworkGrid(props) {
  return (
    <Row>
      {props.artworks.map((artwork) => (
        <Col md={6} lg={4} xl={3} style={{ padding: "1rem 0" }}>
          <Artwork artwork={artwork} />
        </Col>
      ))}
    </Row>
  );
}

export default ArtworkGrid;
