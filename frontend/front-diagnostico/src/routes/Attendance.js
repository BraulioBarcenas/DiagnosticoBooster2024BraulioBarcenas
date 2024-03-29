import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import EnrolledUserItem from '../components/EnrolledUserItem/EnrolledUserItem';
import FormTemplate from '../components/FormTemplate/FormTemplate';
import { Box, Stack, Table } from '@mui/joy';

function Attendance(params) {
    const { id } = useParams();
    const [attendance, setAttendance] = useState([]);
    const [isLoading, setLoading] = useState(true);

    useEffect(() => {
        axios.get('http://localhost:8080/api/sessions/attendance?id_session=' + id)
            .then(response => {
                setAttendance(response.data.datos[0]);
                setLoading(false)
            })
            .catch(error => {
                console.error(error);
            });
    }, [id]);


    if (isLoading) {
        return (
            <>
                <p>LOADING......</p>
            </>
        )
    }

    return (
        <Stack direction={'row'}>
            <Box
                marginLeft={'3%'}
                width={'20%'}
            >            
                <h1>Buscar usuario</h1>
                <FormTemplate action='/api/users/byPhone' type='searchUsers' id_session={id}></FormTemplate>
            </Box>
            <Box
                display="flex"
                justifyContent="center"
                alignItems="center"
                margin={"auto"}
                flexDirection={"column"}
                width={'80%'}
            >
                <h1>Lista de participantes inscritos</h1>
                <Table sx={{ width: 0.8, margin: 'auto' }} variant='soft'>
                    <thead>
                        <tr>
                            <th style={{ width: '40%' }}>Nombre</th>
                            <th>Telefono</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        {attendance.map(enrollment => (
                            <EnrolledUserItem id_user={enrollment.user} key={enrollment.id_enrollment} id_session={id}></EnrolledUserItem>
                        ))}
                    </tbody>
                </Table>
            </Box>

        </Stack>
    );
}


export default Attendance;