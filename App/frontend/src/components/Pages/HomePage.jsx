import React from "react";
import { useState } from "react";
import { Spinner } from "react-bootstrap";
import ArtworkGrid from "../ArtworkGrid";
import Dropdown from "../DropdownMenu";
import ArtworkService from "../../services/ArtworkService";
function HomePage() {
  const [artworks, setArtworks] = useState([]);
  const [loading, setLoading] = useState(false);

  const getArtworks = async (floor, room) => {
    setLoading(true);
    const response = await ArtworkService.getArtworks(floor, room);
    setArtworks(response.data);
    setLoading(false);
  };
  const handleRender = () => {
    if (!loading && artworks.length === 0) {
      return <p>Please select a floor and a room from above dropdown list.</p>;
    } else {
      if (loading)
        return (
          <div>
            <Spinner animation="border" role="status">
              <span className="sr-only">Loading...</span>
            </Spinner>
          </div>
        );
      else return <ArtworkGrid artworks={artworks} />;
    }
  };

  return (
    <React.Fragment>
      <Dropdown getArtworks={getArtworks} />

      <div style={{ paddingTop: "3rem" }}>{handleRender()}</div>
    </React.Fragment>
  );
}

export default HomePage;
