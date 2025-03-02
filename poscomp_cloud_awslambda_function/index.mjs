export const handler = async (event) => {
  try {
    const body = JSON.parse(event.body || '{}');
    if (!body.message) {
      return {
        statusCode: 400,
        body: JSON.stringify({ message: "Missing 'message' field in request body." }),
      };
    }
    const currentYear = new Date().getFullYear();
    const currentPosComp = `POSCOMP ${currentYear}`;
    let responseMessage;
    if (body.message === currentPosComp) {
      responseMessage = `Edital de inscrição para o ${currentPosComp} aberto! Se inscreva em: https://www.sbc.org.br/poscomp/`;
    } else {
      responseMessage = `Edital de inscrição para o ${currentPosComp} ainda NÃO aberto.`;
    }
    return {
      statusCode: 200,
      body: JSON.stringify({ message: responseMessage }),
    };
  } catch (error) {
    return {
      statusCode: 500,
      body: JSON.stringify({ message: "Internal server error", error: error.message }),
    };
  }
};
