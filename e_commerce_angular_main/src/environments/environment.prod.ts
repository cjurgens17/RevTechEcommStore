// export const environment = {
//   production: true
// };
export const environment = {
  production: true,
  withCredentials: true, //baseUrl: "http://localhost:8080", //local host base url 
  baseUrl: "http://54.145.202.78:8080", //EC2 base url
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': 'http://localhost:4200',
  },
};