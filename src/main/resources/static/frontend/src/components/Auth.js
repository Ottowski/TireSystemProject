
import axios from 'axios';

export const createUser = async (user) =>{
try{
    const res = await axios.post('/api/register', user)
    console.log(res)
    return res.data
    

}catch(error){
console.log(error)
}
}


export const loginUser = async (user) => {
  try {
      const res = await axios.post('/api/login', user);
      const currentUser = res.data.user;
      const { jwt } = res.data;
      localStorage.setItem('token', jwt);
        localStorage.setItem('user', JSON.stringify(currentUser));

    console.log(res);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
