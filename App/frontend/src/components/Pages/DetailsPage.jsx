import React, { useState, useEffect } from "react";
import { Col, Image, Row, Spinner, ListGroup } from "react-bootstrap";
import ArtworkService from "../../services/ArtworkService";
import ArtworkGrid from "../ArtworkGrid";
function DetailsPage(props) {
  const [artworks, setArtworks] = useState([]);
  const [loading, setLoading] = useState(true);
  const { artwork } = props.location.state;
  const getRecommendataions = () => {
    setLoading(true);
    ArtworkService.getRecommendedArtworks(artwork).then((res) => {
      setArtworks(res.data);
      setLoading(false);
    });
  };

  useEffect(() => {
    getRecommendataions();
    window.scrollTo(0, 0);
  }, [artwork]);

  const handleLoading = () => {
    if (loading) {
      return (
        <Spinner animation="border" role="status">
          <span className="sr-only">Loading...</span>
        </Spinner>
      );
    } else {
      return <ArtworkGrid artworks={artworks} />;
    }
  };
  return (
    <React.Fragment>
      <h4 style={{ textAlign: "center", paddingTop: "0.5rem" }}>
        Artwork details
      </h4>
      <Row style={{ paddingTop: "2rem" }}>
        <Col lg={5}>
          <Image
            style={{ maxHeight: "25rem" }}
            rounded
            fluid
            src={artwork.image}
          />
        </Col>
        <Col lg={7} style={{ textAlign: "left", padding: "1rem" }}>
          <h5 style={{ paddingTop: "0.5rem" }}>Name</h5>
          <p style={{ fontSize: "18px" }}>{artwork.title}</p>

          <h5 style={{ paddingTop: "0.5rem" }}>Description</h5>
          <p style={{ fontSize: "15px" }}>{artwork.text}</p>
          <h5>Artist</h5>
          <div style={{ textAlign: "center" }}>
            <Image
              style={{ maxHeight: "15rem", maxWidth: "14rem" }}
              roundedCircle
              src={artwork.artistImg}
            />
            <p>{artwork.artistName}</p>
            <p style={{ fontSize: "14px" }}>
              {artwork.artistBirthDate} - {artwork.artistDeathDate}
            </p>
          </div>
          <p style={{ fontSize: "15px" }}>{artwork.artist}</p>
          <h5 style={{ paddingTop: "0.5rem" }}>Published year</h5>
          <p style={{ fontSize: "15px" }}>{artwork.year}</p>

          <h5 style={{ paddingTop: "0.5rem" }}>Other details</h5>
          <ListGroup>
            <ListGroup.Item>
              <b>Dimensions:</b>
              <p style={{ display: "inline" }}>
                {" "}
                {artwork.height} x {artwork.width}
              </p>
            </ListGroup.Item>
            <ListGroup.Item>
              <b>Art type:</b>
              <p style={{ display: "inline" }}> {artwork.type}</p>
            </ListGroup.Item>
            <ListGroup.Item>
              <b>Medium used:</b>
              <p style={{ display: "inline" }}> {artwork.medium}</p>
            </ListGroup.Item>
          </ListGroup>
        </Col>
      </Row>
      <div style={{ paddingTop: "4rem" }}>
        <h4>Recommended Artworks</h4>
      </div>
      {handleLoading()}
    </React.Fragment>
  );
}

export default DetailsPage;
