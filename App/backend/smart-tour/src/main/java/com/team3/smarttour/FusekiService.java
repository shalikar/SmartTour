package com.team3.smarttour;

import org.apache.jena.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FusekiService {

    static String artworkServiceEndpoint = "http://ec2-13-52-214-192.us-west-1.compute.amazonaws.com:3030/ArtworkDS/query";

    public String createArtworksQuery(String floor, String room) {
        String str = "\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"+
                "PREFIX artist: <http://www.semanticweb.org/nikhil'spc/ontologies/2020/10/untitled-ontology-17#>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
                "PREFIX artwork:<http://www.semanticweb.org/suyoghalikar/ontologies/2020/10/Artwork#>\n"+
                "PREFIX artwork2:<http://www.semanticweb.org/suyoghalikar/ontologies/2020/10/untitled-ontology-19#>\n"+
                "PREFIX location:<http://www.semanticweb.org/suyoghalikar/ontologies/2020/10/Location#>\n"+
                "PREFIX ds1: <http://ec2-54-203-213-152.us-west-2.compute.amazonaws.com:3030/LocationDS/>\n"+
                "PREFIX ds2: <http://ec2-13-52-214-192.us-west-1.compute.amazonaws.com:3030/ArtworkDS/>\n"+
                "PREFIX ds3: <http://ec2-18-223-195-11.us-east-2.compute.amazonaws.com:3030/ArtistDS/>\n"+
                "PREFIX art: <http://w3id.org/art/terms/1.0/>\n"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
                "PREFIX ds: <http://purl.org/ctic/dcat#>\n"+
                "\n"+
                "SELECT DISTINCT ?artworkID ?artworkImage ?title ?description ?artistID ?classifiedLabel ?productionDate ?medium ?type ?height ?width ?artistName ?birthDate ?deathDate ?artistImage\n"+
                "WHERE {\n"+
                "SERVICE ds1:sparql {\n"+
                "?location rdf:type location:Location.\n"+
                "?location location:hasLocation ?floor.\n"+
                "?floor rdf:type location:Floor.\n"+
                "?location location:hasLocation ?room.\n"+
                "?room rdf:type location:Room.\n"+
                "?floor location:hasLabel '"+floor+"'.\n"+
                "?room location:hasLabel '"+room+"'.\n"+
                "?location location:hasLocationID ?locationID.\n"+
                "}\n"+
                "\n"+
                "\n"+
                "SERVICE ds2:sparql {\n"+
                "?artwork rdf:type artwork:Artwork.\n"+
                "?artwork artwork:locatedAt ?location_2.\n"+
                "?location_2 artwork:hasLocationID ?locationID.\n"+
                "\n"+
                "?artwork artwork2:hasArtworkID ?artworkID.\n"+
                "?artwork artwork2:hasImageURL ?artworkImage.\n"+
                "?artwork artwork:hasTitle ?title.\n"+
                "?artwork artwork2:hasDescription ?description.\n"+
                "?artwork artwork2:createdBy ?creator.\n"+
                "?creator artwork:hasArtistID ?artistID.\n"+
                "?artwork artwork2:classifiedAs ?classified.\n"+
                "?classified artwork2:hasClassificationLabel ?classifiedLabel.\n"+
                "?artwork artwork2:hasProductionDate ?productionDate.\n"+
                "?artwork artwork:hasMediumDescription ?medium.\n"+
                "?artwork artwork:hasType ?type.\n"+
                "?artwork artwork:hasHeight ?height.\n"+
                "?artwork artwork:hasWidth ?width.\n"+
                "}\n"+
                "\n"+
                "SERVICE ds3:sparql {\n"+
                "?artist rdf:type artist:Artist.\n"+
                "?artist artist:hasArtistID ?artistID.\n"+
                "?artist artist:hasName ?artistName.\n"+
                "?artist artist:birthDate ?birthDate.\n"+
                "?artist artist:deathDate ?deathDate.\n"+
                "?artist artist:hasImageURL ?artistImage.\n"+
                "}\n"+
                "}\n"+
                "LIMIT 30\n";
        return str;
    }



    public String createRecommendationQuery(Artwork artwork) {
        String str = "\n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"+
                "PREFIX artist: <http://www.semanticweb.org/nikhil'spc/ontologies/2020/10/untitled-ontology-17#>\n"+
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
                "PREFIX artwork:<http://www.semanticweb.org/suyoghalikar/ontologies/2020/10/Artwork#>\n"+
                "PREFIX artwork2:<http://www.semanticweb.org/suyoghalikar/ontologies/2020/10/untitled-ontology-19#>\n"+
                "PREFIX ds2: <http://ec2-13-52-214-192.us-west-1.compute.amazonaws.com:3030/ArtworkDS2/>\n"+
                "PREFIX ds3: <http://ec2-18-223-195-11.us-east-2.compute.amazonaws.com:3030/ArtistDS/>\n"+
                "PREFIX art: <http://w3id.org/art/terms/1.0/>\n"+
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
                "PREFIX ds: <http://purl.org/ctic/dcat#>\n"+
                "\n"+
                "SELECT DISTINCT ?artworkID ?artworkImage ?title ?description ?artistID ?classifiedLabel ?productionDate ?medium ?type ?height ?width ?artistName ?birthDate ?deathDate ?artistImage\n"+
                "WHERE {\n"+
                "SERVICE ds3:sparql {\n"+
                "?artist rdf:type artist:Artist.\n"+
                "?artist artist:hasArtistID '" +artwork.getArtistID()+"' .\n"+
                "?artist artist:hasProduced ?artworkID_2.\n"+
                "?artworkID_2 artist:hasArtworkID ?artworkIDLabel.\n"+
                "}\n"+
                "\n"+
                "SERVICE ds2:sparql {\n"+
                "?artwork rdf:type artwork:Artwork.\n"+
                "{\n"+
                "?artwork artwork2:hasArtworkID ?artworkIDLabel.\n"+
                "}\n"+
                "UNION\n"+
                "{\n"+
                "?artwork artwork2:classifiedAs ?classified.\n"+
                "?classified artwork2:hasClassificationLabel '" +artwork.getType()+"'.\n"+
                "?artwork artwork:hasMediumDescription'" +artwork.getMedium()+"'.\n"+
                "?artwork artwork2:hasProductionDate '" +artwork.getYear() + "'.\n"+
                "}\n"+
                "\n"+
                "?artwork artwork:hasTitle ?title.\n"+
                "?artwork artwork:hasHeight ?height.\n"+
                "?artwork artwork:hasWidth ?width.\n"+
                "?artwork artwork2:hasImageURL ?image.\n"+
                "?artwork artwork2:hasDescription ?description.\n"+
                "?artwork artwork2:hasArtworkID ?artworkIDLabel_2.\n"+
                "?artwork artwork2:classifiedAs ?classified_2.\n"+
                "FILTER (?artworkIDLabel_2 != '" +artwork.getArtworkId()+"').\n"+
                "?artwork artwork2:hasArtworkID ?artworkID.\n"+
                "?artwork artwork2:hasImageURL ?artworkImage.\n"+
                "?artwork artwork:hasTitle ?title.\n"+
                "?artwork artwork2:hasDescription ?description.\n"+
                "?artwork artwork2:createdBy ?creator.\n"+
                "?creator artwork:hasArtistID ?artistID.\n"+
                "?artwork artwork2:classifiedAs ?classified.\n"+
                "?classified artwork2:hasClassificationLabel ?classifiedLabel.\n"+
                "?artwork artwork2:hasProductionDate ?productionDate.\n"+
                "?artwork artwork:hasMediumDescription ?medium.\n"+
                "?artwork artwork:hasType ?type.\n"+
                "?artwork artwork:hasHeight ?height.\n"+
                "?artwork artwork:hasWidth ?width.\n"+
                "}\n"+
                "\n"+
                "\n"+
                "SERVICE ds3:sparql {\n"+
                "?artist rdf:type artist:Artist.\n"+
                "?artist artist:hasArtistID ?artistID.\n"+
                "?artist artist:hasName ?artistName.\n"+
                "?artist artist:birthDate ?birthDate.\n"+
                "?artist artist:deathDate ?deathDate.\n"+
                "?artist artist:hasImageURL ?artistImage.\n"+
                "}\n"+
                "}\n"+
                "LIMIT 10\n";
        return str;
    }




    private List<Artwork> loadArtwork(String uri, String query) {

        QueryExecution queryExecution = QueryExecutionFactory.sparqlService(uri, query);
        ResultSet result = queryExecution.execSelect();
        List<Artwork> artworks = new ArrayList<>();

        while (result.hasNext()) {

            Artwork artwork = new Artwork();
            QuerySolution soln = result.nextSolution() ;
            artwork.setImage(soln.getLiteral("artworkImage").toString());
            artwork.setText(soln.getLiteral("description").toString());
            artwork.setArtworkId(soln.getLiteral("artworkID").toString());
            artwork.setArtistId(soln.getLiteral("artistID").toString());
            artwork.setTitle(soln.getLiteral("title").toString());
            artwork.setArtistName(soln.getLiteral("artistName").toString());
            artwork.setYear(soln.getLiteral("productionDate").toString());
            artwork.setType(soln.getLiteral("classifiedLabel").toString());
            artwork.setHeight(soln.getLiteral("height").toString());
            artwork.setWidth(soln.getLiteral("width").toString());
            artwork.setArtistBirthDate(soln.getLiteral("birthDate").toString());
            artwork.setArtistDeathDate(soln.getLiteral("deathDate").toString());
            artwork.setArtistImg(soln.getLiteral("artistImage").toString());
            artwork.setMedium(soln.getLiteral("medium").toString());

            artworks.add(artwork);
        }

        return artworks;
    }


    public List<Artwork> getArtworks(String floorName, String roomName) {
        String query = createArtworksQuery(floorName,roomName);
        return loadArtwork(artworkServiceEndpoint, query);
    }

    public List<Artwork> getRecommendedArtworks(Artwork artwork) {
        String query = createRecommendationQuery(artwork);
        return loadArtwork(artworkServiceEndpoint, query);
    }

}
