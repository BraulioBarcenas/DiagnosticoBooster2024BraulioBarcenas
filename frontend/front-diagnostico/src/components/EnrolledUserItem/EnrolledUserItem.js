import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Button } from '@mui/joy';


function EnrolledUserItem(props) {
  const [userData,setUserData] = useState([]);

  useEffect(() => {
    axios.get('http://localhost:8080/api/users/byId?id_user=' + props.id_user)
      .then(response => {
        setUserData(response.data.datos[0]);
      })
      .catch(error => {
        console.error(error);
      });
  }, [props.id_user])


  return (
    <tr>
      <td>{userData.name}</td>
      <td>{userData.phone}</td>
      <td style={{alignItems:'center'}}><Button color='danger' onClick={async () => {
        if (window.confirm('Seguro que quieres eliminar la inscripcion del usuario '+userData.name)) {
          axios.delete('http://localhost:8080/api/sessions/delete/cancelByUser', {
            data:{
              user: userData.id_user,
              session: props.id_session
            }
          })
          .then((response) => {
            alert('Inscripcion eliminada');
            window.location.reload();
          })
          .catch((error) => {
            alert('Ha ocurrido un error')
            console.log(userData.id_user);
            console.log(props);
            console.log(error);
          })
        }

      }}>Anular Inscripcion</Button></td>
    </tr>
  )
}

export default EnrolledUserItem;
