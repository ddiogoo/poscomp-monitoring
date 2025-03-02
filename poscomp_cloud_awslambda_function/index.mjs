export const handler = async (event) => {
  try {
    const body = JSON.parse(event.body || '{}');
    if (!body.message) {
      return {
        statusCode: 400,
        body: JSON.stringify({ message: "Missing 'message' field in request body." }),
      };
    }
    return {
      statusCode: 200,
      body: JSON.stringify({ message: body.message }),
    };
  } catch (error) {
    return {
      statusCode: 500,
      body: JSON.stringify({ message: "Internal server error", error: error.message }),
    };
  }
};
