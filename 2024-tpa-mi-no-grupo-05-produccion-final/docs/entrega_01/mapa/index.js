let map;

async function initMap() {
  //@ts-ignore
  const { Map } = await google.maps.importLibrary("maps");
  const { AdvancedMarkerElement } = await google.maps.importLibrary("marker");

  map = new Map(document.getElementById("map"), {
    center: { lat: -34.6078, lng: -58.4462 },
    zoom: 12.3,
    mapId: "4504f8b37365c3d0"
  });
  const marker1 = new AdvancedMarkerElement({
    map,
    position: { lat: -34.6594, lng: -58.4706 },
    title: "UTN FRBA - Sede Campus"
  });
  const marker2 = new AdvancedMarkerElement({
    map,
    position: { lat: -34.5984, lng: -58.4215 },
    title: "UTN FRBA - Sede Medrano"
  });
}


initMap();