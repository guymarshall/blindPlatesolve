// All measurements are in pixels (i32)

mod fits_reader;

use std::{collections::HashMap, fs::File, io::Read};

use crate::fits_reader::get_headers;

#[derive(Debug)]
struct Coordinates {
    x: i32,
    y: i32,
}

#[derive(Debug)]
struct Star {
    coordinates: Coordinates,
    size: i32,
}

#[derive(Debug)]
struct Image {
    width: i32,
    height: i32,
    stars: Vec<Star>,
}

fn main() {
    // TODO REMOVE: get test file path
    let mut file: File = File::open("test_file_path.txt").unwrap(); // path to test .fits file
    let mut buffer: Vec<u8> = Vec::new();
    file.read_to_end(&mut buffer).unwrap();
    let test_file_path: String = buffer.try_into().unwrap();

    // open image
    let headers: Vec<(String, String)> = get_headers(&test_file_path);
    let header_map: HashMap<String, String> = headers.into_iter().collect();
    let image: Image = Image {
        width: header_map["NAXIS1"].parse().unwrap(),
        height: header_map["NAXIS2"].parse().unwrap(),
        stars: vec![],
    };

    println!("{:?}", image);

    // identify stars
    // find brightest star
    // look at 4 nearest stars to that brighest star and measure distances between them (hash code)
    // repeat these steps for the stars in the star database
    // star database hash codes will be compared with the image hash codes until some matches are found.
    // Once some matches are found it is possible to calculate the precise position of the image with the matching database stars.
}
