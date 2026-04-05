use std::fs::File;
use std::io::{BufReader, Read};

pub(crate) fn get_headers(path: &str) -> Vec<(String, String)> {
    let file: File = File::open(path).unwrap();
    let mut reader: BufReader<File> = BufReader::new(file);

    let mut headers: Vec<(String, String)> = Vec::new();
    let mut block: [u8; 2880] = [0u8; 2880];

    loop {
        reader.read_exact(&mut block).unwrap();

        for chunk in block.chunks(80) {
            let line: String = String::from_utf8_lossy(chunk).to_string();

            let keyword: String = line[0..8].trim().to_string();

            if keyword == "END" {
                return headers;
            }

            if let Some(eq_pos) = line.find('=') {
                let value: String = line[eq_pos + 1..]
                    .split('/')
                    .next()
                    .unwrap_or("")
                    .trim()
                    .to_string();

                headers.push((keyword, value));
            }
        }
    }
}
