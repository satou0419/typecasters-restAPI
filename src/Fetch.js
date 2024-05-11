import { useEffect, useState } from "react";
import Axios from "axios";

export default function Fetch() {
  const [count, setCount] = useState(1);
  const [objectEnemy, setObjectEnemy] = useState([]);

  useEffect(() => {
    Axios.get(
      `https://api-typecasters.azurewebsites.net/adventure_enemy/get_enemy_by_floor_id?floor_id=${count}`
    ).then((response) => {
      setObjectEnemy(response.data);
    });
  }, []);

  return (
    <>
      <div>
        <button>Count</button>
        <p>{count}</p>
        <p>ID: {objectEnemy[0].towerFloorId}</p>
      </div>
    </>
  );
}
