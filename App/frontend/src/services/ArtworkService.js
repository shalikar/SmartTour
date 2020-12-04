import Axios from "axios";

const baseUrl = "http://localhost:8080";

class ArtworkService {
  getRecommendedArtworks = (artwrok) => {
    return Axios.post(baseUrl + "/artworks/recommended/", artwrok);
  };
  getArtworks = (floor, room) => {
    return Axios.get(baseUrl + `/artworks/${floor}/${room}`);
    // return artworks;
  };
}

export default new ArtworkService();
