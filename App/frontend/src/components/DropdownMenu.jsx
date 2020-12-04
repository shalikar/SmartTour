import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";

function DropdownMenu(props) {
  const [room, setRoom] = useState(101);
  const [floor, setFloor] = useState(1);
  const [rooms, setRooms] = useState([
    101,
    102,
    103,
    104,
    105,
    106,
    107,
    108,
    109,
    110,
  ]);
  const handleSubmit = (event) => {
    event.preventDefault();
    event.stopPropagation();
    props.getArtworks(floor, room);
  };
  const updateRoom = (event) => {
    setRoom(event.target.value);
  };
  const updateFloor = (event) => {
    event.preventDefault();
    event.stopPropagation();
    let temp = [];
    let floorNo = Number(event.target.value);
    for (let i = 1; i < 11; i++) {
      temp.push(floorNo * 100 + i);
    }
    setRooms(temp);
    setFloor(event.target.value);
  };

  return (
    <Form style={{ paddingTop: "3rem" }} onSubmit={handleSubmit}>
      <Form.Group controlId="form.FloorSelect">
        <Form.Label>Select Floor</Form.Label>
        <Form.Control as="select" onChangeCapture={updateFloor}>
          <option>1</option>
          <option>2</option>
          <option>3</option>
          <option>4</option>
          <option>5</option>
        </Form.Control>
      </Form.Group>
      <Form.Group controlId="form.RoomSelect">
        <Form.Label>Select Room</Form.Label>
        <Form.Control as="select" onChangeCapture={updateRoom}>
          {rooms.map((room) => (
            <option>{room}</option>
          ))}
        </Form.Control>
      </Form.Group>
      <Button variant="primary" type="submit">
        Submit
      </Button>
    </Form>
  );
}

export default DropdownMenu;
