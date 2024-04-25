export function colorPalette(numColors) {
    let colors = [];
    let accentGreen = "#8BC387";
    let leafGreen = "#2d850e";
    let scale = chroma.scale([accentGreen, leafGreen]).mode('lch').colors(numColors);

    for (let i = 0; i < numColors; i++) {
        let color = scale[i];
        colors.push(color);
    }
    return colors;
}
export function colorPaletteBorders(colorPalette) {
    let colors = [];
    for (let i = 0; i < colorPalette.length; i++) {
        let color = chroma(colorPalette[i]).darken().hex();
        colors.push(color);
    }
    return colors;
}