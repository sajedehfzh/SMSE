import React from "react";
import { waitFor } from "@testing-library/react";
import axios from "axios";
import UploadExcel from "./UploadExcel";

jest.mock("axios");

// Mock FileReader API
global.FileReader = jest.fn(() => ({
  readAsBinaryString: jest.fn(),
  onload: jest.fn(),
}));


describe("UploadExcel", () => {
    beforeEach(() => {
      render(<UploadExcel />);
    });
  
    afterEach(() => {
      jest.clearAllMocks();
    });
  
    it("should render file input", () => {
      const fileInput = screen.getByLabelText("Upload Excel");
      expect(fileInput).toBeInTheDocument();
      expect(fileInput).toHaveAttribute("type", "file");
    });
  
    it("should handle file upload", () => {
        const file = new File(["test"], "test.xlsx", { type: "application/vnd.ms-excel" });
        const fileInput = screen.getByLabelText("Upload Excel");
      
        fireEvent.change(fileInput, { target: { files: [file] } });
      
        // Assert the expected behavior after file upload
        const readerInstance = global.FileReader.mock.instances[0];
        expect(readerInstance.readAsBinaryString).toHaveBeenCalledWith(file);
      });
  
      it("should delete a row", () => {
        render(<UploadExcel />);
        const deleteButton = screen.getByText("Delete");
        const rowToDelete = deleteButton.closest("tr");
      
        fireEvent.click(deleteButton);
      
        // Assert the expected behavior after deleting a row
        expect(rowToDelete).not.toBeInTheDocument();
      });

  
   it("should edit a row", () => {
  render(<UploadExcel />);
  const editButton = screen.getByText("Edit");

  fireEvent.click(editButton);

  // Assert the expected behavior after editing a row
  const editInput = screen.getByRole("textbox");
  expect(editInput).toBeInTheDocument();
});

it("should save a row", () => {
    render(<UploadExcel />);
    const saveButton = screen.getByText("Save");
  
    fireEvent.click(saveButton);
  
    // Assert the expected behavior after saving a row
    const saveButtonInEditMode = screen.queryByText("Save");
    expect(saveButtonInEditMode).not.toBeInTheDocument();
  });
  
  it("should handle input change", () => {
    render(<UploadExcel />);
    const editButton = screen.getByText("Edit");
  
    fireEvent.click(editButton);
  
    const editInput = screen.getByRole("textbox");
    fireEvent.change(editInput, { target: { value: "New Value" } });
  
    // Assert the expected behavior after changing an input value
    expect(editInput).toHaveValue("New Value");
  });
  
  
  it("should handle form submission", async () => {
    const sampleData = [
      [1, "John", "Doe", 9.5, 5, 8.2, 3, 7.1, 4, 8.8, 6, 9.2, 7]
      // Add more sample data rows as needed
    ];
  
    render(<UploadExcel />);
    const submitButton = screen.getByText("Submit");
  
    fireEvent.click(submitButton);
  
    // Assert the expected behavior after form submission
    await waitFor(() => {
      expect(axios.post).toHaveBeenCalledTimes(1);
      expect(axios.post).toHaveBeenCalledWith("http://localhost:8080/uploadExcel", expect.any(Array));
      // TODO: Check if the response is logged or handled properly
    });
  });