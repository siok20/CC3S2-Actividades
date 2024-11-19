const request = require('supertest');
const app = require('../src/app');


describe('GET/', ()=>{
    let server;

    beforeAll(() => {
        server = app.listen(0);
    });

    afterAll(() => {
        server.close();
    });

    it('should return Hello, World!', async()=>{
        const res = await request(app).get('/');
        expect(res.statusCode).toEqual(200);
        expect(res.text).toBe('Hello, World!');
    });

    it('should 7*8 = 56 return Producto: 56 ', async()=>{
        const res = await request(app).get('/multi/7/8');
        expect(res.statusCode).toEqual(200);
        expect(res.text).toBe('Producto: 56');
    });
});
