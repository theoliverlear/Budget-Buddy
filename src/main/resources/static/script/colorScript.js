//================================-Functions-=================================

//-------------------------------Color-Palette--------------------------------
export function colorPalette(numColors) {
    // Create a color palette with the number of colors specified with the
    // range from accent green to leaf green.
    let colors = [];
    let accentGreen = "#8BC387";
    let leafGreen = "#2d850e";
    // Create a color scale from accent green to leaf green with the number of
    // colors specified.
    let scale = chroma.scale([accentGreen, leafGreen]).mode('lch').colors(numColors);
    // Add each color in the scale to the color palette.
    for (let i = 0; i < numColors; i++) {
        let color = scale[i];
        colors.push(color);
    }
    // Return the color palette.
    return colors;
}
//---------------------------Color-Palette-Borders----------------------------
export function colorPaletteBorders(colorPalette) {
    // Take a color palette and create a new color palette with the colors
    // darkened for borders.
    let colors = [];
    // Loop through each color in the color palette and darken it for the
    // border color palette.
    for (let i = 0; i < colorPalette.length; i++) {
        let color = chroma(colorPalette[i]).darken().hex();
        colors.push(color);
    }
    // Return the color palette for borders.
    return colors;
}